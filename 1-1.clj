(ns lab1.core
  (:gen-class))

(defn f1 [w alph]
    ;(println w alph)
    (if (> (count alph) 0)
        (if (not= (first w) (first alph))
            (concat (list (cons (first alph) w))
                    (f1 w (rest alph)))
            (f1 w (rest alph))
        )
        '()
    )
)
(defn f2 [w alph]
    ;(println w alph)
    (if (empty? w)
        []
        (concat
            (f1 w alph)
            (f2 (rest w) alph))))


(println "f1" (f1 '() '(:a :b :c)))    ;((b a) (c a))
(println "f1" (f1 '(:a) '(:a :b :c)))    ;((b a) (c a))
;(println "f1" (f1 '(:a :b) '(:a :b :c))) ;((b a) (c a))
;(println "f2" (f2 '(:a :b) '(:a :b :c))) ;((b a) (c a) (a b) (c b))
