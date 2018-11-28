## Sprint #2 Retrospect

We started with Mad, Sad, Glad again, but since all but one cards were in "Glad", we didn't spend too much time trying to find improvements other than *do more of the same!*

### Glad Topics
* Balanced workload, equal participation, team worked well together.
* Scheduling was even through the week and between devs.
* We delivered what was asked in great fashion and without deadline driven development!

### Mad Topics
* Silent Failing on web tests: <form> hiding inside a <p> element caused tests to fail for apparently no reason. This was a pain to debug.
	* We discussed about adding HTML-validating system to our process, but decided that's of low priority, especially since we were unsure if it would catch this kind of a problem. On a more general level, it's relevant to consider the whole render process and be aware of e.g. JS affecting the end result.
