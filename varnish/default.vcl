vcl 4.0;

import directors;

backend server1 {
  .host = "app";
  .port = "8080";
  .probe = {
    .url = "/status";
    .timeout = 1s;
    .interval = 3s;
    .window = 5;
    .threshold = 3;
  }
}

backend server2 {
  .host = "appreplica";
  .port = "8080";
  .probe = {
    .url = "/status";
    .timeout = 1s;
    .interval = 3s;
    .window = 5;
    .threshold = 3;
  }
}

sub vcl_init {
  new dir = directors.round_robin();
  dir.add_backend(server1);
  dir.add_backend(server2);
}

sub vcl_recv {
  set req.backend_hint = dir.backend();
}

sub vcl_backend_response {
  set beresp.ttl = 1s;
}