
(ns syllabification
  (:require [clojure.string :as str]))

(def text "this is a sample text")

(defn transform [text]
  (map #(if (= %1 " ") " "
            (if (re-matches #"[aeiou]" %1) "V" "C"))
            (str/split text #"")))

(println (transform text))
