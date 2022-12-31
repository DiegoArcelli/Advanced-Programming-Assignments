import MultiSet
import System.IO.Unsafe
import Data.List

getstrings :: [Char] -> IO [[Char]]
getstrings filename = do
  text <- readFile filename
  return (words text)

getciaostrings :: [Char] -> [[Char]]
getciaostrings filename = map (\x->sort x) (unsafePerformIO (getstrings filename))

readMSet :: [Char] -> MSet [Char]
readMSet filename = foldr (\x mset -> add mset x) (MS []) (getciaostrings filename)

getline :: (Show a, Show b) => a -> b -> [Char]
getline val mult = "<" ++ (show val) ++ "> - <" ++ (show mult) ++ ">\n"

gettext :: (Foldable l, Show a, Show b) => l (a, b) -> [Char]
gettext words = foldr (\(val, mult) text ->  text ++ (getline val mult) ) "" words

writeMSet :: MSet [Char] -> [Char] -> IO ()
writeMSet (MS x) filename = writeFile filename (gettext x)


main :: IO()
main = do
  writeMSet (readMSet "margana2.txt") "prova.txt"