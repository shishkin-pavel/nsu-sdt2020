(ns laba1-1.core
  (:gen-class))

(defn make_list [word alf]                  ;k - длинна алфавита n- счетчик перечисления алф
  (let [num (count alf)]
    (if (= (first word) (first alf)) 
      (if (> num 1) (make_list word (rest alf)))
      
      (let [search (cons (first alf) word)]
        (if (= num 1) (list search)
            (cons search (make_list word (rest alf)))))))
  )

;(make_list '() '(:a :b :c) 2) 

(defn multi_make_list [words alf];n_words-начиная с конца-1 до 0  n- счетчик перечисления алф
    (if (not= (count words) 1) (concat (make_list (first words) alf) (multi_make_list (rest words) alf))
      (make_list (first words) alf)))
  

;(multi_make_list (list '()) '(:a :b :c) 2 0); 

(defn mix [words width alf count_width ];count_width=1
  (if (= count_width 1) (let [zamena (multi_make_list words alf)] (mix zamena width alf (+ count_width 1)))
      (if (not= count_width width)
        (let [zamena (multi_make_list words alf)]
          (mix zamena width alf (+ count_width 1)))
        (multi_make_list words alf))))

;(mix (list '()) 3 '(:a :b :c) 1 2); итоговая функция   (слово ширина_последовательностей алф счетчик_ширины счетчик_алфовита)

(defn main [width alf]
  (mix '(()) width alf 1))

(main 2 '(:a :b :c))
