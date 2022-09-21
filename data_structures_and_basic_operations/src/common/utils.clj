(ns common.utils)

(defn my-contains?
  ([coll val] (my-contains? coll val 0))
  ([coll val index]
   (if (not (= (get coll index)))
     (if (= index (- (count coll) 1))
       false
       (recur coll val (inc index)))
     true)))

(defn contains-all?
  ([coll1 coll2] (contains-all? coll1 coll2 0))
  ([coll1 coll2 index]
   (if (my-contains? coll1 (get coll2 index))
     (if (= index (- (count coll2) 1))
       true
       (recur coll1 coll2 (inc index)))
     false)))
