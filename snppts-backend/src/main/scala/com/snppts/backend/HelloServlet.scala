package com.snppts.backend

import org.scalatra._

class HelloServlet extends SnpptsbackendStack {

  get("/") {
    <html>
      <body>
        <h1>Bonjur, snppts!</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

}
