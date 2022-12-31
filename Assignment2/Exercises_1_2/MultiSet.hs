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
-- mset1 = MS [(1, 10), (2, 15), (3, 7), (4, 3)]
-- mset2 = MS [(1, 10), (2, 15), (3, 7), (4, 5), (10, 7)]


getlist :: MSet a -> [(a, Int)]
getlist (MS x) = x

add :: Eq a => MSet a -> a -> MSet a
add (MS (x:xs)) v = if (v == fst x)
    then MS ( [(fst x, snd x + 1)] ++ xs )
    else MS ( [(fst x, snd x)] ++ (getlist (add (MS xs) v)))
add (MS []) v = MS [(v, 1)]


occs :: Eq a => MSet a -> a -> Int
occs (MS (x:xs)) v = (if v == fst x then snd x else 0) + (occs (MS xs) v)
occs (MS []) _ = 0


elems :: MSet a -> [a]
elems (MS (x:xs)) = [fst x] ++ elems (MS xs)
elems (MS []) = []



isin :: Eq a => (a, Int) -> MSet a -> Bool
isin (v, n) (MS (x:xs)) = if ((v,n) == x)
    then True
    else isin (v, n) (MS xs)
isin (v, n) (MS []) = False


subeq :: Eq a => MSet a -> MSet a -> Bool
subeq (MS (x:xs)) mset2 = if (isin x mset2)
    then subeq (MS xs) mset2
    else False
subeq (MS []) mset2 = True


addMult :: Eq a => MSet a -> (a, Int) -> MSet a
addMult (MS (x:xs)) (v, n) = if (v == fst x)
    then MS ( [(fst x, snd x + n)] ++ xs )
    else MS ( [(fst x, snd x)] ++ (getlist (addMult (MS xs) (v, n))))
addMult (MS []) (v, n) = MS [(v, n)]


union :: Eq a => MSet a -> MSet a -> MSet a
union (MS (x:xs)) mset2 = union (MS xs) (addMult mset2 x)
union (MS []) mset2 = mset2


instance Foldable MSet where
    foldr f acc (MS []) = acc
    foldr f acc (MS (x:xs)) = f (fst x) (foldr f acc (MS xs))


instance Eq a => Eq (MSet a) where
    (==) (MS []) (MS []) = True
    (==) (MS (x:xs)) (MS (y:ys)) = (x == y) && (xs == ys)
    (==) _ _ = False


mapMSetRec :: (a -> b) -> MSet a -> MSet b
mapMSetRec f (MS (x:xs)) = MS ( [ (f (fst x), snd x)] ++ (getlist (mapMSetRec f (MS xs))) )
mapMSetRec f (MS []) = MS []

mapMSet :: Eq b => (a -> b) -> MSet a -> MSet b
mapMSet f (MS x) = union (mapMSetRec f (MS x)) (MS [])