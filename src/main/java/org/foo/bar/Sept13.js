/*
Write a function that returns the longest non-repeating substring for a string input.
Examples
longestNonrepeatingSubstring("abcabcbb") --> "abc"
longestNonrepeatingSubstring("aaaaaa") --> "a"
longestNonrepeatingSubstring("abcde") --> "abcde"
longestNonrepeatingSubstring("abcda") --> "abcd"
*/

const longestNonrepeatingSubstringCases = [
  ['abcabcbb', 'abc'],
  ['aaaaaa', 'a'],
  ['abcde', 'abcde'],
  ['abcda', 'abcd'],
]

function longestNonrepeatingSubstring(s) {
  const hash = Array(128).fill(-1)
  let last = -1,
    size = 0,
    res = 0
  for (let i = 0; i < s.length; ++i) {
    const c = s.charCodeAt(i)
    if (hash[c] > last) {
      last = hash[c]
    }
    hash[c] = i
    if (size < i - last) {
      size = i - last
      res = last + 1
    }
  }
  return s.substring(res, res + size)
}

for (const c of longestNonrepeatingSubstringCases) {
  console.log(longestNonrepeatingSubstring(c[0]) === c[1])
}
