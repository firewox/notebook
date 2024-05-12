export function leadingSlash (str) {
  return str.startsWith('/') ? str : '/' + str
}

export function trailingSlash (str) {
  if (str.endsWith('.jpg')) {
    return str
  } else {
    return str + '/'
  }
}

export const wait = timeout => {
  return new Promise(resolve => setTimeout(resolve, timeout))
}
