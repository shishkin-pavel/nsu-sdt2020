(ns task-1-1.core
  (:gen-class))

(defn connect-word-alphabet [word alphabet]
  (if (> (count alphabet) 0)
    (if (= (last word) (first alphabet))
      (connect-word-alphabet word (rest alphabet))
      (cons (concat word (list (first alphabet))) (connect-word-alphabet word (rest alphabet))))
    ()))
;(connect-word-alphabet '(:c) '(:a :b :c))

(defn connect-words-alphabet [words alphabet]
  (if (> (count words) 0)
    (concat (connect-word-alphabet (first words) alphabet) (connect-words-alphabet (rest words) alphabet))
    ()))
;(connect-words-alphabet '((:a :c) (:b :c)) '(:a :b :c))

(defn task-1-1 [alphabet n]
  (if (> n 1)
    (connect-words-alphabet (task-1-1 alphabet (dec n)) alphabet)
    (connect-words-alphabet `(()) alphabet)))
;(task-1-1 '("a" 1 :b) 2)