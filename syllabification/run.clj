
(ns syllabification
  (:require [clojure.string :as str]))

;(def text (str/lower-case
;"Oft have we trod the vales of Castaly
;And heard sweet notes of sylvan music blown
;From antique reeds to common folk unknown:
;And often launched our bark upon that sea
;Which the nine Muses hold in empery,
;And ploughed free furrows through the wave and foam,
;Nor spread reluctant sail for more safe home
;Till we had freighted well our argosy.

;Of which despoiled treasures these remain,
;Sordello's passion, and the honied line
;Of young Endymion, lordly Tamburlaine
;Driving his pampered jades, and more than these,
;The seven-fold vision of the Florentine,
;And grave-browed Milton's solemn harmonies."))

(def text "oft have we trod the vales of castaly")

(defn transform [text]
  (map #(if (= %1 " ") " "
            (if (re-matches #"[aeiou]" %1) "V" "C"))
            (filter #(re-matches #"[a-z ]" %1) (str/split text #""))))

(defn chunk [text]
  (defn space? [c]
    (= c " "))
  (defn elements [el s]
    (count (filter #(= %1 el) s)))
  (defn split [s]
    (if (> (elements "V" s) 1)
      (partition-by #(= %1 "V") s)
          s))

  (map split
       (filter #(not-every? space? %1) (partition-by #(= %1 " ") text))))


(println (chunk (transform text)))
