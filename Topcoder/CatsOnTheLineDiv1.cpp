/*
 * @author: Anirudh Rayabharam <anirudh.rayabharam@gmail.com
 * @handle: code_overlord
 */
#include <vector>
#include <algorithm>

using namespace std;

class CatsOnTheLineDiv1 {
public:
    int getNumber( vector <int> position, vector <int> count, int t ) {
    	int ans = 0;
    	int n = position.size();
    	vector< pair<int, int> > v(n);

    	for (int i = 0; i < n; ++i) {
    		v[i] = make_pair(position[i], count[i]);
    	}

    	sort(v.begin(), v.end());

    	int left = (int) -(1e9);
    	int from, to, cnt;
    	bool bad = false;
    	for (int i = 0; i < n; ++i) {
    		from = v[i].first - t;
    		to = v[i].first + t;
    		cnt = v[i].second;

    		if (left >= from && bad) continue;

    		if (left >= from) {
    			from = left + 1;
    		}

    		int canGo = from + cnt - 1;
    		if (canGo > to) {
    			left = to;
    			bad = true;
    			++ans;
    		} else {
    			left = canGo;
    			bad = false;
    		}
    	}

    	return ans;
    }
};
