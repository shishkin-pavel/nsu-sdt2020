(ns task-1-4.core
  (:gen-class))

(defn connect-word-alphabet [word alphabet]
   (map #(concat word (list %)) (filter #(not= (last word) %) alphabet)))
;(connect-word-alphabet '(:c) '(:a :b :c))

(defn connect-words-alphabet [words alphabet]
  (mapcat #(connect-word-alphabet % alphabet) words))
;(connect-words-alphabet '((:a :c) (:b :c)) '(:a :b :c))

(defn task-1-4 [alphabet n]
  (nth (iterate #(connect-words-alphabet % alphabet) '(())) n))
;(task-1-4 '("a" 1 :b) 3)