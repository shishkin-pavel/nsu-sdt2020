(ns testproject.lab14)

(defn unlist [result]
  (loop [res result, acc '()]
    (if (= (count res) 0)
      acc
      (recur (rest res) (concat acc (first res))))))

(defn check-cond [result]
  (not= (first result) (first (rest result)))
  )

(defn add-to-list [symbol]
  (loop [ch '((a) (b) (c)), acc '()]
    (if (= (count ch) 0)
      acc
      (recur (rest ch) (concat acc (list (concat (first ch) symbol)))))))

(defn generate [res n_left]
  (if (= n_left 0)
    res
    (do
      (def res2 (unlist (map add-to-list res)))
      (println res2)
      (def res2 (filter check-cond res2))
      (println res2)
      (recur res2 (dec n_left))
      )
    )
  )

(defn -main
  [& args]
  (generate '(()) 4))
