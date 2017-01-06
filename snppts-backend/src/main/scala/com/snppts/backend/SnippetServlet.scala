package com.snppts.backend

import org.scalatra._

class SnippetServlet extends SnpptsbackendStack {

  get("/") {
    <html>
      <body>
        <h1>Hello, world!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
