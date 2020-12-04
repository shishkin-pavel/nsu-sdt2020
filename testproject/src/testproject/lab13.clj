(ns testproject.lab13)

(defn my-reduce
  ([f initial coll]
   (loop [result initial
          remaining coll]
     (if (empty? remaining)
       result
       (recur (f result (first remaining)) (rest remaining)))))
  ([f [head & tail]]
   (my-reduce f head tail)))

(defn my-filter [p coll]
  (reduce
    (fn [acc c] (if (p c) (conj acc c) acc))
    []
    coll))

(defn my-map [f res choices]
  (reduce
    (fn [acc c] (concat acc (f c choices)))
    []
    res))

(defn check-cond [result]
  (not= (first result) (first (rest result)))
  )

(defn add-to-list [symbol choices]
  (loop [ch choices, acc '()]
    (if (= (count ch) 0)
      acc
      (recur (rest ch) (concat acc (list (concat (first ch) symbol)))))))

(defn generate [res n_left choices]
  (if (= n_left 0)
    res
    (do
      (def res2 (my-map add-to-list res choices))
      (println res2)
      (def res2 (my-filter check-cond res2))
      (println res2)
      (recur res2 (dec n_left) choices))
    )
  )

(defn -main
  [& args]
  ;(println (my-map inc [1 2 3]))
  ;(println (add-to-list "a" ["a" "b" "c"]))
  ;(println (generate ["a" "b" "c"] 3))
  ;(def res ["a" "b" "c"])
  ;(def res ["a" "b" "c"])
  (generate '(()) 3 '((a) (b) (c)))
  ;(println (my-filter (fn [x]
  ;                      (= (count x) 1))
  ;                    ["a" "aa" "b" "n" "f" "lisp" "clojure" "q" ""])))
  )
