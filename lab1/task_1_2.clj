(ns task-1-2.core
  (:gen-class))

(defn connect-word-alphabet [word alphabet]
  (loop [temp-word word temp-alphabet alphabet result ()]
  (if (> (count temp-alphabet) 0)
    (if (= (last temp-word) (first temp-alphabet))
      (recur temp-word (rest temp-alphabet) result)
      (recur temp-word (rest temp-alphabet) (concat result (list (concat temp-word (list (first temp-alphabet)))))))
    result)))
;(connect-word-alphabet '(:c) '(:a :b :c))

(defn connect-words-alphabet [words alphabet]
  (loop [temp-words words temp-alphabet alphabet result ()]
  (if (> (count temp-words) 0)
    (recur (rest temp-words) temp-alphabet (concat result (connect-word-alphabet (first temp-words) temp-alphabet)))
    result)))
;(connect-words-alphabet '((:a :c) (:b :c)) '(:a :b :c))

(defn task-1-2 [alphabet n]
  (loop [temp-alphabet alphabet temp-n n result '(())]
  (if (> temp-n 0)
    (recur temp-alphabet (dec temp-n) (connect-words-alphabet result temp-alphabet))
    result)))
;(task-1-2 '("a" 1 :b) 2)