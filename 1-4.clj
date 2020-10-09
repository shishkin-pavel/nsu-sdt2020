(ns lab1.core
  (:gen-class))
  (defn f1
    [w alph]
    (map
        #(cons % w)
        (filter #(not= (first w) %) alph)
    )
)

(println (f1 `() `(:a :b :c)))
(println (f1 `(:a) `(:a :b :c)))
