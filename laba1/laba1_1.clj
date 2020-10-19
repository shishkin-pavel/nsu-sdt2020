(ns laba1-1.core
  (:gen-class))

(defn make_list [word alf]          
  (let [num (count alf)]
    (if (= (first word) (first alf)) 
      (if (> num 1) (make_list word (rest alf))
          alf)
      (let [search (cons (first alf) word)]
        (if (= num 1) (list search)
            (cons search (make_list word (rest alf)))))))
  )

;(make_list '() '(:a :b :c)) 

(defn multi_make_list [words alf];
    (if (not= (count words) 1) (concat (make_list (first words) alf) (multi_make_list (rest words) alf))
      (make_list (first words) alf)))
  

;(multi_make_list '(()) '(:a :b :c))

(defn mix [words width alf count_width];count_width=1
  (let [zamena (multi_make_list words alf)]
    (if (= count_width 1) (mix zamena width alf (+ count_width 1))
        (if (>= count_width width)  (multi_make_list words alf)
            (mix zamena width alf (+ count_width 1))
        ))))

;(mix (list '()) 3 '(:a :b :c) 1); 

(defn main [width alf]
  (if (= (count alf) 0) (println "errore")
    (mix '(()) width alf 1))
  )

(main 2 '())

