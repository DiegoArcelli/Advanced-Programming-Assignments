module MultiSet(
    MSet(..),
    empty,
    add,
    occs,
    elems,
    subeq,
    union,
    mapMSet,
    mapMSetRec
) where


empty :: MSet a
empty = MS []

data MSet a = MS [(a, Int)] deriving (Show)

-- extract the list from the MSet
getList :: MSet a -> [(a, Int)]
getList (MS x) = x

{-- 
recursively scan the MSet to check if the elment is already present in MSet.
If the element is found its multiplicity is increased by 1 and the recursion is stopped.
If the element is not in the MSet it is added with multilplicity 1
--}
add :: Eq a => MSet a -> a -> MSet a
add (MS ((e, c):xs)) v = if (v == e)
    then MS ( [(e, c + 1)] ++ xs )
    else MS ( [(e, c)] ++ (getList (add (MS xs) v)))
add (MS []) v = MS [(v, 1)]


{-- 
recursively scan the list of the MSet, if the first element of the pair (value, multiplicity)
is equal to the passed value v, then the second element of the pair is returned, 
otherwise 0 is returned at the end
--}
occs :: Eq a => MSet a -> a -> Int
occs (MS ((e, c):xs)) v = (if v == e then c else (occs (MS xs) v)) 
occs (MS []) _ = 0

{--
apply the map combinator to the list of the MSet returing
a list containing only the first element of each pair
--}
elems :: MSet a -> [a]
elems (MS list) = map (\(e, c) -> e) list

{--
function to check if an element is present in the MSet with a given multilplicity.
The list of the MSet is scanned recursively and if one of the pair (value, multiplicity)
is equal to the passed pair then True is returned and the recusion is stopped,
otherwise False is returned
--}
isIn :: Eq a => (a, Int) -> MSet a -> Bool
isIn (v, n) (MS (x:xs)) = if ((v,n) == x)
    then True
    else isIn (v, n) (MS xs)
isIn (v, n) (MS []) = False


{--
recursively scan the list of the first MSet and check if an element with its mutiplicity 
is present also in the secon MSet (using the isIn function). If an pair of the first MSet
is not present in the second MSet then the recusion is stopped and False is returned,
otherwise True at the end.
--}
subeq :: Eq a => MSet a -> MSet a -> Bool
subeq (MS (x:xs)) mset2 = if (isIn x mset2)
    then subeq (MS xs) mset2
    else False
subeq (MS []) mset2 = True



{--
function to add an element v to the MSet but with a given mulitplicity n. 
The function works like the add function but in this case if the element is found instead
of increasing the multiplicity by 1, it is increased by n. If the element is not found 
is added to the MSet with mutiplicity n
--}
addMult :: Eq a => MSet a -> (a, Int) -> MSet a
addMult (MS ((e, c):xs)) (v, n) = if (v == e)
    then MS ( [(e, c + n)] ++ xs )
    else MS ( [(e, c)] ++ (getList (addMult (MS xs) (v, n))))
addMult (MS []) (v, n) = MS [(v, n)]


{--
Function that implements the union of two MSets.
The foldr scan all the pairs (element, multiplicity) of the first MSet
and it adds them to the second MSet (which is the starting value of the accumulator)
using the function addMult
--}
union :: Eq a => MSet a -> MSet a -> MSet a
union (MS list) mset = foldr (\x acc -> addMult acc x) mset list



{--
Foldr is implemented for MSet like it would be implemented for list, but only the first element
of each pair of the MSet is considered when the binary function f is applied to the
current value of the accumulator and the element of the MSet
--} 


foldrMSet :: (a -> b -> b) -> b -> MSet a -> b
foldrMSet f acc (MS []) = acc
foldrMSet f acc (MS ((e, c):xs)) = f e (foldr f acc (MS xs))

instance Foldable MSet where
    foldr f acc mset = foldrMSet f acc mset


{--
The equality two MSets is checked verifying verifying if each element of mset1 is also an
element of mset2 with the same multiplicity and also the viceversa. 
This is achived using the previously defined subeq function in both directions
--}
instance Eq a => Eq (MSet a) where
    (==) mset1 mset2 = (subeq mset1 mset2) && (subeq mset2 mset1)



{--
function which applies a function f to the first element of each pair of the MSet
--}
mapMSetRec :: (a -> b) -> MSet a -> MSet b
mapMSetRec f (MS list) = MS ( map (\(e, c) -> (f e, c))  list)


{-- 
It is not possible to define an instance of Functor for MSet,
since the signature of the function should be:
(a -> b) -> MSet a -> MSet b
but in order to use map on the MSet we need the signature:
Eq b = > (a -> b) -> MSet a -> MSet b
since it could happen that if we have two pairs in the MSet (v_1, c_1) and (v_2, c_2) and
the function could map both v1 and v2 to v, and at this point we have to merge the two
pairs in a unique pair (v, c_1 + c_2). Of course in order to do this the type of b support equality

For the implementation, first the mapMSetRec function is called to apply the passed function f
to all the elements of the MSet without merging duplicate pairs. Then we apply the union to the 
MSet returned by mapMSetRec and and emply MSet, so that all the duplicate paris are merged
--}
mapMSet :: Eq b => (a -> b) -> MSet a -> MSet b
mapMSet f (MS x) = union (mapMSetRec f (MS x)) (MS [])