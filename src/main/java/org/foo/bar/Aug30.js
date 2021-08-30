/**
The additive persistence of an integer, n, is the number of times you have to
replace n with the sum of its digits until n becomes a single digit integer.

The multiplicative persistence of an integer, n, is the number of times you have
to replace n with the product of its digits until n becomes a single digit
integer.

Create two functions that take an integer as an argument and:

Return its additive persistence.
Return its multiplicative persistence.
Examples: Additive Persistence
additivePersistence(1679583) --> 3
// 1 + 6 + 7 + 9 + 5 + 8 + 3 = 39
// 3 + 9 = 12
// 1 + 2 = 3
// It takes 3 iterations to reach a single-digit number.

additivePersistence(123456) --> 2
// 1 + 2 + 3 + 4 + 5 + 6 = 21
// 2 + 1 = 3

additivePersistence(6) --> 0
// Because 6 is already a single-digit integer.
Examples: Multiplicative Persistence
multiplicativePersistence(77) --> 4
// 7 x 7 = 49
// 4 x 9 = 36
// 3 x 6 = 18
// 1 x 8 = 8
// It takes 4 iterations to reach a single-digit number.

multiplicativePersistence(123456) --> 2
// 1 x 2 x 3 x 4 x 5 x 6 = 720
// 7 x 2 x 0 = 0

multiplicativePersistence(4) --> 0
// Because 4 is already a single-digit integer.
Notes
The input n is never negative.
 */

const additivePersistenceArgs = [
  [1679583, 3], //
  [123456, 2], //
  [6, 0], //
]
const multiplicativePersistenceArgs = [
  [123456, 2], //
  [4, 0], //
]

function additivePersistence(n) {
  const sum = (n) => {
    let res = 0
    while (n > 0) {
      res += n % 10
      n = Math.floor(n / 10)
    }
    return res
  }
  let cnt = 0
  while (Math.floor(n / 10) > 0) {
    ++cnt
    n = sum(n)
  }
  return cnt
}

function multiplicativePersistence(n) {
  const mul = (n) => {
    let res = 1
    while (n > 0) {
      res *= n % 10
      n = Math.floor(n / 10)
    }
    return res
  }
  let cnt = 0
  while (Math.floor(n / 10) > 0) {
    ++cnt
    n = mul(n)
  }
  return cnt
}

for (const arg of additivePersistenceArgs) {
  console.log(additivePersistence(arg[0]) === arg[1])
}

for (const arg of multiplicativePersistenceArgs) {
  console.log(multiplicativePersistence(arg[0]) === arg[1])
}
