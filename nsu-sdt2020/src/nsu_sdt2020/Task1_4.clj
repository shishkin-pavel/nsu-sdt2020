(ns Task1_4)

(defn simplify-structure [result]
  (loop [res result, acc '()]
    (if (= (count res) 0)
      acc
      (recur (rest res) (concat acc (first res))))))

(defn predicat-not-equals [result]
  (not= (first result) (first (rest result))))

(defn add-new-symbol [symbol]
  (loop [ch '((a) (b) (c)), acc '()]
    (if (= (count ch) 0)
      acc
      (recur (rest ch) (concat acc (list (concat (first ch) symbol)))))))

(defn task1 [res n_left]
  (if (= n_left 0)
    res
    (do
      (println "  ----New Round [" (- 3 n_left) "]----")
      (def longer-map (map add-new-symbol res))
      (println "longer-map  " longer-map)
      (def simplified (simplify-structure longer-map))
      (println "simplified  " simplified)
      (def filtered (filter predicat-not-equals simplified))
      (println "filtered    " filtered)
      (println)
      (recur filtered (dec n_left))
)))

(defn -main
  [& args]
  (task1 '(()) 2))