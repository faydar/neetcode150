# Kth Largest Element in a Stream

## `https://leetcode.com/problems/kth-largest-element-in-a-stream/description/`

## `https://neetcode.io/problems/kth-largest-integer-in-a-stream?list=neetcode150`

Idea is to create a min heap (although problem asks for nth maximum), and store only k elements.
so at any time, the root of the heap - which is the minimum of the all numbers stored - holds the Kth maximum.

We are storing K top elements, with minimum of them being the heap root.

Both custom heap and PQ impls are done
