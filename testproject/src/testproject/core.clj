(ns testproject.core)

(defn foo
  [choices res]
    (if (> (count choices) 0)
      (if (not= (first choices) (first res))
        (concat (list (cons (first choices) res)) (foo (rest choices) res))
        (foo (rest choices) res))
      )
)

(defn foo2
  [choices result]
  (if (> (count result) 0)
    (concat (foo choices (first result)) (foo2 choices (rest result)))
    )
  )

(defn generate
  "Список `choices` -- алфавит.
  `n_left` -- длина слов."
  [choices n_left]
  (:pre [
         (>= n_left 0)])
  (cond
    (= n_left 0) '(())
    :else
    (foo2 choices (generate choices (dec n_left)))))

(defn -main
  [& args]
  (println (foo `(:a :b :c) `()))
  (println (foo2 `(:a :b :c) `((:a) (:b))))
  (println (generate '((a) (b) (c)) 3)))

