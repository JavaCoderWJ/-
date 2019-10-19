export function throttle(method, context,delay = 100) {
  clearTimeout(method.tId);
  method.tId = setTimeout(function() {
    method.call(context);
  }, delay);
}