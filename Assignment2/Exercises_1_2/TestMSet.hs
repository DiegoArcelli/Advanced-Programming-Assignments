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

readfiles :: MSet [Char] -> MSet [Char] -> MSet [Char] -> MSet [Char] -> IO ()
readfiles m1 m2 m3 m4 = if ( m1 /= m4 && (elems m1) == (elems m4) )
  then if ( m1 == (MultiSet.union m2 m3) )
    then do
      writeMSet m1 "anag-out.txt"
      writeMSet m4 "gana-out.txt"
      return ()
    else return ()
  else
    return ()


main :: IO()
main = do
  readfiles (readMSet "./aux_files/anagram.txt") (readMSet "./aux_files/anagram-s1.txt") (readMSet "./aux_files/anagram-s2.txt") (readMSet "./aux_files/margana2.txt")
  return ()