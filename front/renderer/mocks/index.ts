if (typeof window === 'undefined') {
  const server = require('./server');
  // console.log(''server);
  // server.listen();
} else {
  const { worker } = require('./browsers');
  worker.start();
}
export {};
