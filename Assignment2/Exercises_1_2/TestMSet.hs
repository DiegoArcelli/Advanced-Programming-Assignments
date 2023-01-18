import MultiSet
import System.IO.Unsafe
import Data.List

{--
Function to read the content of the auxiliary files and return and IO moand of base 
type of list of strings.
I assumed that in the file which is read by the function each line contains
just one word like in the auxiliary files
--}
getStrings :: [Char] -> IO [[Char]]
getStrings filename = do
  text <- readFile filename
  return (words text)


{--
Function which extract from the IO monad the list of words and for each of them it computes its CIAO
The unsafePerformIO can be used since the file doesn't change during the execution of the program
--}
getCiaoStrings :: [Char] -> [[Char]]
getCiaoStrings filename = map (\x->sort x) (unsafePerformIO (getStrings filename))



{--
Function which starting from an empty MSet add the CIAO of each word read from 
the file corresponding to the passed file name 
--}
readMSet :: [Char] -> MSet [Char]
readMSet filename = foldr (\x mset -> add mset x) (MS []) (getCiaoStrings filename)


{--
Function used to produce a line of the file while writing an MSet,
with the format: <element> - <multiplicity> 
--}
getFileLine :: (Show a, Show b) => a -> b -> [Char]
getFileLine val mult = "<" ++ (show val) ++ "> - <" ++ (show mult) ++ ">\n"


{--
Function which produces the text to write on the file, concatenating the strings that
reprsente the elements of the MSet
--}
getText :: (Foldable l, Show a, Show b) => l (a, b) -> [Char]
getText words = foldr (\(val, mult) text ->  text ++ (getFileLine val mult) ) "" words


{--
Function which takes in input an MSet of strings and writes it in a file
where each line represent an element of the MSet
--}
writeMSet :: MSet [Char] -> [Char] -> IO ()
writeMSet (MS x) filename = writeFile filename (getText x)


{--
Function which take as input four MSet of strings: m1, m2, m3, m4
It write m1 and m4 as files only if m1 is different from m4, but they contain the
same elements, and if the union of m2 and m3 is equal to m1
--}
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


{-- 
Main function to call the readFiles function on the four auxiliary files
--}
main :: IO()
main = do
  readFiles (readMSet "./aux_files/anagram.txt") (readMSet "./aux_files/anagram-s1.txt") (readMSet "./aux_files/anagram-s2.txt") (readMSet "./aux_files/margana2.txt")