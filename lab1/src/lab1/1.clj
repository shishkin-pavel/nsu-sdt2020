(ns lab1)

(defn addElement 
  ([permutation elems] (addElement permutation '() elems))
  ([permutation permutations elems]
  (if (empty? elems)
    permutations
      (addElement permutation 
                  (if (= (first permutation) (first elems))
                    permutations
                    (cons (concat (list (first elems)) permutation) permutations)) (rest elems)))))

(defn forEachPermutation 
  ([permutations elems] (forEachPermutation permutations '() elems))
  ([permutations result elems]
  (if (empty? permutations)
    result
   (forEachPermutation (rest permutations) (concat result(addElement (first permutations) elems)) elems))))

(defn permutate 
  ([elems k] (permutate elems (addElement '() elems) (dec k)))
  ([elems permutations k]
   (if (= k 0)
     permutations
     (permutate elems (forEachPermutation permutations elems) (dec k)))))

(defn main [k & elems]
  (if (and (> k 0) (<= k (count elems)))
    (permutate elems k)
    "Incorrect input"))

(main 2 1 2 3 4)