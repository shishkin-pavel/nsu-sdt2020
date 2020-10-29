(ns lab1)

(defn addElement [permutation elems]
   (loop [permutations '() elems elems]
     (if (empty? elems)
       permutations
       (recur (if (= (first permutation) (first elems))
                permutations
                (cons (concat (list (first elems)) permutation) permutations)) (rest elems)))))

(defn forEachPermutation 
  ([permutations elems]
   (loop [result '() permutations permutations]
     (if (empty? permutations)
       result
       (recur (concat result (addElement (first permutations) elems)) (rest permutations))))))

(defn permutate 
  ([elems k]
   (loop [permutations (addElement '() elems) k (dec k)]
   (if (== k 0)
     permutations
     (recur (forEachPermutation permutations elems) (dec k))))))

(defn main [k & elems]
  (if (and (> k 0) (<= k (count elems)))
    (permutate elems k)
    "Incorrect input"))

(main 2 1 2 3 4)