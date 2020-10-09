(ns lab1.core
  (:gen-class))


(defn f1 [w alph]
    ;(println w alph)
    (if (empty? alph)
        '()
        (if (not= (first w) (first alph))
            (concat (list (cons (first alph) w))
                    (f1 (rest alph) w))
            (f1 w (rest alph)))))

(defn f2 [w alph]
    ;(println w alph)
    (if (empty? w)
        []
        (concat
            (f1 w alph)
            (f2 (rest w) alph))))


;---

(defn lab0 [word lst]
  (if (empty? lst) []
      (if (= (str (nth (first word) 0)) (str (first lst)))
        (lab0 word (rest lst))
        (cons (cons (first lst) (first word)) (lab0 word (rest lst))))))

(defn lab01
  ([word lst]
   (if (empty? word)
     []
     (concat (lab0 word lst) (lab01 (rest word) lst)))))

;---

(println "f1" (f1 '() '(:a :b :c)))    ;((b a) (c a))
(println "f1" (f1 '(:a) '(:a :b :c)))    ;((b a) (c a))
;(println "f1" (f1 '(:a :b) '(:a :b :c))) ;((b a) (c a))
;(println "f2" (f2 '(:a :b) '(:a :b :c))) ;((b a) (c a) (a b) (c b))

;(println "Antoha f1" (lab0 '("a") '("a" "b" "c")))
;(println "Antoha f1" (lab0 '("a" "b") '("a" "b" "c")))
;(println "Antoha f2" (lab01 '("a" "b") '("a" "b" "c")))
