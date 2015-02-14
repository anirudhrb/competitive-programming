#include <cstring>
#include <vector>
#include <list>
#include <map>
#include <set>
#include <deque>
#include <stack>
#include <bitset>
#include <algorithm>
#include <functional>
#include <numeric>
#include <utility>
#include <sstream>
#include <iostream>
#include <iomanip>
#include <cstdio>
#include <cmath>
#include <cstdlib>
#include <ctime>
#include <memory.h>
#include <cassert>

using namespace std;

const int N = 10000001;

bool mark[N];
int p[N], ct[N][8];

int main() {
  mark[1] = false;
  for (int i = 2; i < N; ++i) {
    if (mark[i]) continue;
    for (int j = 1; i * j < N; ++j) {
      mark[i * j] = true;
      p[i * j]++;
    }
  }
  for (int i = 2; i < N; ++i) {
    for (int j = 0; j < 8; ++j)
      ct[i][j] = ct[i - 1][j];
    ct[i][p[i] - 1]++;
  }
  int t, a, b, k;
  scanf("%d", &t);
  for (int tt = 1; tt <= t; ++tt) {
    scanf("%d %d %d", &a, &b, &k);
    printf("Case #%d: ", tt);
    if (k > 8) printf("0\n");
    else {
      int ans = ct[b][k - 1] - ct[a - 1][k - 1];
      printf("%d\n", ans);
    }
  }
  return 0;
}