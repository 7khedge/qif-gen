(ns qif-gen.core
  (:gen-class)
  (:require [qif-gen.qif-generator :refer :all]
            [qif-gen.transaction-file :refer :all]))

(defn getQifTransactions [transactions]
  (map map-to-qif-string (map transaction-string-to-map transactions)))

(defn convertToQifTransactionFile [filepath]
  (writeQifTransactions (qifFilename filepath) (getQifTransactions (readTransactions filepath))))

(defn -main
  "Runs qif generator. Usuage <full file path to transaction file>"
  [& args]
  (convertToQifTransactionFile (first args)))



