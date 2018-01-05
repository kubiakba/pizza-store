const PROXY_CONFIG = [
  {
    context: [
      "/orders",
      "/users",
      "/products"
    ],
    target: "http://localhost:8081",
    secure: false
  }
]

module.exports = PROXY_CONFIG;
