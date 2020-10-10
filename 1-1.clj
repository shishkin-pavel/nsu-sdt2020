(ns lab1.core
  (:gen-class))

(defn f1 [w alph]
    ;(println "f1" w alph)
    (if (empty? alph)
        '()
        (if (not= (first w) (first alph))
            (cons (cons (first alph) w)
                    (f1 w (rest alph)))
            (f1 w (rest alph))
        )
    )
)
(defn f2 [w alph]
    ;(println "f2" w alph)
    (if (empty? w)
        '()
        (concat
            (f1 (first w) alph)
            (f2 (rest w) alph)
        )
    )
)

(defn f3 [n alph]
    ;(println "f3+" n alph)
    (if (> n 1)
        (f2
            (f3 (dec n) alph)
            alph
        )
        (f2 '(()) alph))
)


;(println "f1 a" (f1 '() '(:a :b :c)))      ;((a) (b) (c))
;(println "f1 b" (f1 '(:a) '(:a :b :c)))    ;((b a) (c a))

;(println "f2" (f2 '((:a) (:b)) '(:a :b :c)))   ;((b a) (c a) (a b) (c b))
;(println "f2+" (f2 '(()) '(:a :b :c)))

(println "f3 a" (f3 2 '(:a :b :c)))
(println "f3 b" (f3 2 '(:a [:b :c] "d")))
