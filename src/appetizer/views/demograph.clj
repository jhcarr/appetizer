(ns appetizer.views.demograph
  (:require [hiccup.core :as html]
            [net.cgrand.enlive-html :as enlive]
            [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql])
  (:use [appetizer.models.db :only (db)]))

(defn get-unique-products
  [database]
  (let [sql-fn-keyword (keyword "COUNT(version)")
        sql-query (sql/select [:product :version sql-fn-keyword] :checkins "GROUP BY product, version")]
    (jdbc/query database sql-query)))

(defn compose-graph-elems []

  (comment "This section composes graph elements we define in D3.")
  
  [:table {:class "table table-striped"}
   [:thead [:th "Product"] [:th "Version"] [:th "Checkins"]]
   [:tbody
    (for [product (get-unique-products (db))]
      [:tr {}
       [:td (product :product)]
       [:td (product :version)]
       [:td (product :count)]])]])

(defn make-demograph []
  (enlive/emit*
    (enlive/at (enlive/html-resource "appetizer/views/layout.html")
               [:div#main]
               (enlive/content (enlive/html (compose-graph-elems))))))
