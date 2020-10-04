(ns laba1-4.core
  (:gen-class))
 
(defn add_letter [word alf]
  (map #(cons % word) (filter #(not= (first word) %) alf)))

(defn comb_word [words alf]
  (reduce concat (map #(add_letter % alf) words)))

(defn mixer [w alf]
  (loop [width w buffer (list '())] 
    (if (> width 1) (recur (- width 1) (comb_word buffer alf))
        (comb_word buffer alf))
    ))

(mixer 3 '(:a :b :c))



