(ns testproject.lab12)

(defn add-element
  ([choices res]
   (loop [ch choices, acc '()]
     (if (= (count ch) 0)
       acc
       (if (not= (first ch) (first res))
         (recur (rest ch) (concat acc (list (cons (first ch) res))))
         (recur (rest ch) acc))))))

(defn add-from-choices
  ([choices result]
   (loop [ch choices, res result, acc '()]
     (if (= (count res) 0)
       acc
       (recur ch (rest res) (concat (add-element ch (first res)) acc ))))))

(defn generate
  "Список `choices` -- алфавит.
  `n_left` -- длина слов."
  [choices n_left]
  (loop [acc '(()), n n_left]
    (if (= n 0)
      acc
      (recur (add-from-choices choices acc) (dec n)))))

(defn -main
  [& args]
  (println (add-element `(:a :b :c) `()))
  (println (add-from-choices `(:a :b :c) `((:a) (:b))))
  (println (generate '((a) (b) (c)) 3)))
