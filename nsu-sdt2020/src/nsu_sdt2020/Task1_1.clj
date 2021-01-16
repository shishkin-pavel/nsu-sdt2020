(ns Task1-1)

(defn addLetter  [elements res]
  (if (> (count elements) 0)
    (if (not(.equals (first elements) (first res)))
      (concat (list (cons (first elements) res)) (addLetter (rest elements) res))
      (addLetter (rest elements) res))  ))

(defn buildWord [elements result]
  (if (> (count result) 0)
    (concat (addLetter elements (first result))
            (buildWord elements (rest result)))   ))

(defn task1 [elements len]
  (cond
    (= len 0) '(())
    (> len 0) (buildWord elements (task1 elements (dec len))))  )

(defn -main
  [& args]
  (println (task1 '(:a :b :c) 3))
  (println (task1 '(a b c) 3))
  (println (task1 '((a) (b) (c)) 3))  )
