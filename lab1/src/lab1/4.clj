(ns lab1)

(defn addElement [permutation elems]
   (loop [permutations '() elems elems]
     (if (empty? elems)
       permutations
       (map #(cons % permutation) (filter #(not (= (first permutation) %)) elems)))))

(defn permutate 
  ([elems k]
   (loop [permutations (addElement '() elems) k (dec k)]
   (if (== k 0)
     permutations
     (recur (mapcat #(addElement % elems) permutations) (dec k))))))

(defn main [k & elems]
  (if (and (> k 0) (<= k (count elems)))
    (permutate elems k)
    "Incorrect input"))

(main 2 1 2 3 4)