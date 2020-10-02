(ns laba1-1.core
  (:gen-class))

(defn make_list [word alf n]                  ;k - длинна алфавита n- счетчик перечисления алф
  (let [search (cons (nth alf n) word)]
    (if (= (first word) (nth alf n))
      (if (> n 0) (make_list word alf (- n 1)));вторая проверка для того что бы не уйти за грань алфавита :a :a:b:c
      (if (= n 0) (list search)
          (concat (list search) (make_list word alf (- n 1)))))))
;(make_list '() '(:a :b :c) 2) 

(defn multi_make_list [words alf n n_words];n_words-начиная с конца-1 до 0  n- счетчик перечисления алф
  (if (not= n_words 0) (concat (make_list (nth words n_words) alf n) (multi_make_list words alf n (- n_words 1)))
      (make_list (nth words n_words) alf n)))

;(multi_make_list (list '()) '(:a :b :c) 2 0); 

(defn mix [words width alf count_width n_alf]; n_alf- счетчик перечисления алф count_width=1
  (if (= count_width 1) (let [zamena (multi_make_list words alf n_alf 0)] (mix zamena width alf (+ count_width 1) n_alf))
      (if (not= count_width width)
        (let [zamena (multi_make_list words alf n_alf (- (count words) 1))]
          (mix zamena width alf (+ count_width 1) n_alf))
        (multi_make_list words alf n_alf (- (count words) 1)))))

;(mix (list '()) 3 '(:a :b :c) 1 2); итоговая функция   (слово ширина_последовательностей алф счетчик_ширины счетчик_алфовита)

(defn main [width alf]
  (mix (list '()) width alf 1 (- (count alf) 1)))

(main 2 '(:a :b :c))

