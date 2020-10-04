(ns laba1-2.core
  (:gen-class))

(defn make_list [word alf n_alf]
  (loop [num n_alf res-coll (list)]
    (let [search (cons (nth alf num) word)]
      (if (= (first word) (nth alf num)) (if (> num 0) (recur (- num 1) res-coll) res-coll);
        (if (= num 0) (cons search res-coll )
            (recur (- num 1) (cons search res-coll)))))
    )
  )
  
;(make_list '() '(:a :b :c) 2) 

(defn multi_make_list [words alf n_alf n_words]
  (loop [num n_words res-coll (list)]
    (if (not= num 0) (recur (- num 1) (concat (make_list (nth words n_words) alf n_alf)  res-coll))
        (concat  (make_list (nth words n_words) alf n_alf) res-coll) ;(concat  (make_list (nth words n_words) alf n_alf) res-coll)
        )))

;(multi_make_list (list '()) '(:a :b :c) 2 0) 

(defn mix [words width alf count_width n_alf]  ; n_alf- счетчик перечисления алф count_width=1
  (if (= count_width 1) (let [zamena (multi_make_list words alf n_alf 0)] (recur zamena width alf (+ count_width 1) n_alf))
      (if (not= count_width width)
        (let [zamena (multi_make_list words alf n_alf (- (count words) 1))]
          (recur zamena width alf (+ count_width 1) n_alf))
        (multi_make_list words alf n_alf (- (count words) 1)))))

;(mix (list '()) 3 '(:a :b :c) 1 2); итоговая функция   (слово ширина_последовательностей алф счетчик_ширины счетчик_алфовита)

(defn main [width alf]
  (mix (list '()) width alf 1 (- (count alf) 1)))


(main 2 '(:a :b :c))