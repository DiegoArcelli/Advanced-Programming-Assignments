import MultiSet
import System.IO.Unsafe
import Data.List

getStrings :: [Char] -> IO [[Char]]
getStrings filename = do
  text <- readFile filename
  return (words text)

getCiaoStrings :: [Char] -> [[Char]]
getCiaoStrings filename = map (\x->sort x) (unsafePerformIO (getStrings filename))

readMSet :: [Char] -> MSet [Char]
readMSet filename = foldr (\x mset -> add mset x) (MS []) (getCiaoStrings filename)

getFileLine :: (Show a, Show b) => a -> b -> [Char]
getFileLine val mult = "<" ++ (show val) ++ "> - <" ++ (show mult) ++ ">\n"

getText :: (Foldable l, Show a, Show b) => l (a, b) -> [Char]
getText words = foldr (\(val, mult) text ->  text ++ (getFileLine val mult) ) "" words

writeMSet :: MSet [Char] -> [Char] -> IO ()
writeMSet (MS x) filename = writeFile filename (getText x)

readFiles :: MSet [Char] -> MSet [Char] -> MSet [Char] -> MSet [Char] -> IO ()
readFiles m1 m2 m3 m4 = if ( m1 /= m4 && (elems m1) == (elems m4) )
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
  readFiles (readMSet "./aux_files/anagram.txt") (readMSet "./aux_files/anagram-s1.txt") (readMSet "./aux_files/anagram-s2.txt") (readMSet "./aux_files/margana2.txt")