# Girajira [![Build Status](https://travis-ci.org/drayah/girajira.svg?branch=master)](https://travis-ci.org/drayah/girajira) [![codecov](https://codecov.io/gh/drayah/girajira/branch/master/graph/badge.svg)](https://codecov.io/gh/drayah/girajira)

Girajira is a small clojure web api that automates Jira card transitions via opened github pull requests.

## Prerequisites

- JDK 8 or above
- [Leiningen 2.8.1](https://leiningen.org/) or above.

## Testing & Plugins

Girajira uses [Midje](https://github.com/marick/Midje) for automated tests.

Recommended user plugins are:
- [CIDER nREPL](https://github.com/clojure-emacs/cider-nrepl)
- [Ultra](https://github.com/venantius/ultra)

A sample `~/.lein/profiles.clj`:
```clojure
{:user {:plugins [[cider/cider-nrepl "0.16.0"]
                  [venantius/ultra "0.5.2"]]
        :dependencies [[org.clojure/tools.nrepl "0.2.13"]]}}
```
**Running tests using Midje**

Enter the project directory and run the tests by using the `lein midje` command in your terminal.

**Running tests automatically**

Midje allows you to automatically run your tests whenever you change your source code. To automatically run your tests use the `lein midje :autotest` command.

**Running code coverage**

We're using `cloverage` for code coverage. Generate the coverage report by running `lein cloverage --runner :midje` in your terminal.

## Configuring Aero

Girajira uses [Aero](https://github.com/juxt/aero) for enviroment configuration. In order to be able to perform requests to Jira's API you need to setup a `.secrets.edn` configuration file in the project's `config` directory. This file will be ignored by `git`.

A sample `config/.secrets.edn`:
```clojure
{:jira
  {:url "https://YOUR_JIRA_URL"
   :username "JIRA_USERNAME"
   :password "JIRA_PASSWORD"}}
```

## Running

To start a web server for the application, run:

    lein ring server

## License

MIT
