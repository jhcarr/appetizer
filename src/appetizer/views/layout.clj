(ns appetizer.views.layout
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql])
  (:use compojure.core
        [net.cgrand.enlive-html :only ()]
        [hiccup.core :only (html)]
        [hiccup.page :only (html5)]))

(defn get-unique-products
  [database]
  (let [sql-query (sql/select [:product :version] :releases)]
    (jdbc/query database sql-query)))

(defn make-table-products
  [database]
  [:table {:class "table table-striped"}
   [:thead [:th "Product"] [:th "Version"]]
   [:tbody
  (for [product (get-unique-products database)]
    [:tr {} [:td (product :product)] [:td (product :version)]])]])

(def db
  {:classname "org.postgresql.Driver"
  :subprotocol "postgresql"
  :subname "//localhost:5432/dujourdb"
  :user "aroetker"
  :password "'"})

(defn template
  "Hiccup template"
  []
  (html  "<!DOCTYPE html>"
         [:html
          {:lang "en"}
          [:head {}
           [:meta {:charset "utf-8"}]
           [:title {} "Appetizer, from Puppet Labs"]
           [:meta
            {:name "viewport",
             :content "width=device-width, initial-scale=1.0"}]
           [:meta {:name "description", :content ""}]
           [:meta {:name "author", :content ""}]
           "<!-- Le styles -->"
           [:link {:href "/css/bootstrap.css", :rel "stylesheet"}]
           [:link {:href "/css/general.css", :rel "stylesheet"}]
           [:link {:href "/css/bootstrap-responsive.css", :rel "stylesheet"}]]
          [:body {}
           [:div {:class "navbar navbar-inverse navbar-fixed-top"}
            [:div {:class "navbar-inner"}
             [:div {:class "container"}
              [:button
               {:type "button",
                :class "btn btn-navbar",
                :data-toggle "collapse",
                :data-target ".nav-collapse"}
               [:span {:class "icon-bar"}]
               [:span {:class "icon-bar"}]
               [:span {:class "icon-bar"}]]
              [:a {:class "brand", :href "#"}
               [:img {:class "logoImage logoImageHeader",
                      :src "/img/puppetlabslogo.png",
                      :alt "Puppet Labs"}]
               "Appetizer"]
              [:div {:class "nav-collapse collapse"}
               [:ul {:class "nav"}
                [:li {:class "active"} [:a {:href "#"} "Overview"]]
                [:li {} [:a {:href "#about"} "About"]]
                [:li {} [:a {:href "#contact"} "Contact"]]]]
              "<!--/.nav-collapse -->"]]]
           [:div {:class "container"}
            [:h1 {} "Appetizer is a dashboard for Dujour"]
            [:p {} "This is a temporary dashboard." [:br {}]
             " All you get is this message and a barebones HTML document."] ]
           "<!-- /container -->"
           [:div {:id "main"} 
            (make-table-products db) ;;[:li {} ""]
            ]
           "<!-- Le javascript\n    ================================================== -->"
           "<!-- Placed at the end of the document so the pages load faster -->"
           [:script {:src "/js/bootstrap.min.js"}]]]))
