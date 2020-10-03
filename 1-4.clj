(ns lab1.core
  (:gen-class))

(defn in?
  [coll e]
  (some #(= e %) coll))

(defn mix
  [alph lett]
  (do
    ;(println alph lett)
    ;                     kak sdelat #() nado li?
    (reduce (fn [acc e] (concat (map (fn [x] (concat x e))
                                (filter (fn [x] (not (in? x e)))
                                        lett))
                         acc))
            (cons '() alph))))


(defn permutation
  [alph, k]
  (if (<= k 0)
      '()
      (reduce (fn [acc i] (mix alph acc))
              (cons alph (range (dec k))))))

; po4emu tolko so strokami robit
(println (permutation '("1" "2" "3") 2))

(#(println %1 %&) '(1 2 3) '(4 5) '(6 7))


