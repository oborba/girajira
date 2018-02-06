(ns girajira.integrations.jira.request-test
  (:require [midje.sweet :refer :all]
            [girajira.integrations.jira.request :refer :all]
            [girajira.integrations.jira.authentication :as jira-authentication])
  (:use clj-http.fake))

(facts "when getting the jira api url"
  (fact "it returns the url with /rest/api/2 appended"
    (jira-api-url) => "http://url/rest/api/2"
    (provided (jira-authentication/url) => "http://url")))

(with-fake-routes
  {"http://jira.com/get"
   (fn [request] {:status 200
                  :headers {}
                  :body "{\"fulano\": \"some fake json\"}"})}

  (facts "when performing an authenticated get request"
    (fact "it returns the parsed response body"
      (authenticated-get "http://jira.com/get") => {"fulano" "some fake json"}
      (provided (jira-authentication/basic-auth-params) => ["user" "pass"]))))

(with-fake-routes
  {"http://jira.com/post"
   (fn [request] {:status 204
                  :headers {}
                  :body "{\"result\": \"fake result\"}"})}

  (facts "when performing an authenticated post request given a request body"
    (fact "it returns a valid response"
      (let [request-body {:payload "fake payload"}]
       (authenticated-post "http://jira.com/post" request-body) => {:status 204 :body {"result" "fake result"}}
       (provided (jira-authentication/basic-auth-params) => ["user" "pass"])))))
