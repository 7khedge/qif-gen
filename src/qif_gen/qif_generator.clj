(ns qif-gen.qif-generator
  (:require [clojure.string :as str]))

(defn transaction-string-to-map [transaction]
  (let [fields (str/split transaction #"\t")]
    (cond (= (count fields) 5 ) {:date (fields 0) :description (fields 2) :amount (fields 4) :type "Withdrawal" }
          (= (count fields) 4 ) {:date (fields 0) :description (fields 2) :amount (fields 3) :type "Deposit"}
          :esle {})))

(defn map-to-qif-string [transaction]
  (str/join "\n" ["!Type:CCard"
                  (str "D" (transaction :date))
                  (str "P" (str/trim (transaction :description)))
                  "M"
                  (str "T" (transaction :amount))
                  (str "N" (transaction :type))
                  "L"
                  "^\n"]))
