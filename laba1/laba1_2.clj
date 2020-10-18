(ns laba1-2.core
  (:gen-class))

(defn make_list [word alf]
  (loop [alph alf res-coll (list)]
    (let [search (cons (first alph) word)
          num (count alph)]
      (if (= (first word) (first alph)) (if (> num 1) (recur (rest alph) res-coll) res-coll);
          (if (= num 1) (cons search res-coll)
              (recur (rest alph) (cons search res-coll)))))))

;(make_list '() '(:a :b :c)) 

(defn multi_make_list [words alf]
  (loop [l_word words res-coll (list)]
    (let [num (count l_word)]
      (if (not= num 1) (recur (rest l_word) (concat (make_list (first l_word) alf)  res-coll))
        (concat  (make_list (first l_word) alf) res-coll)
        )))
    )

;(multi_make_list '(()) '(:a :b :c)) 

(defn mix [words width alf count_width]  ; n_alf- счетчик перечисления алф count_width=1
  (if (= count_width 1) (let [zamena (multi_make_list words alf)] (recur zamena width alf (+ count_width 1)))
      (if (not= count_width width)
        (let [zamena (multi_make_list words alf)]
          (recur zamena width alf (+ count_width 1)))
        (multi_make_list words alf))))

;(mix (list '()) 3 '(:a :b :c) ); итоговая функция   (слово ширина_последовательностей алф счетчик_ширины счетчик_алфовита)

(defn main [width alf]
  (mix '(()) width alf 1))



;(make_list '(:F) '(:a :b :c))
;(multi_make_list '((:a) (:b) (:c)) '(:a :b :c))
;(main 2 '(:a :b :c))