## Initial Database Design

In the initial database design we have a table for the different reading tip types (TipType) and a table for the actual reading tips (Tip).
In addition we have three sub tables (TipAuthor, TipTag, TipCourse) for the tip properties that might have multiple values.
TipType and TipCourse tables will be prefilled so that predefined tip types and courses already exist in the application.
Tip tags (TipTag) will be provided via input by the end user.

Initial database diagram:

![DB diagram](http://yuml.me/09375d70.png)

yuml.me definition:
```
[TipType|id:integer; name:string; hasisbn:boolean; hasurl:boolean]
[Tip|id:integer; tiptype_id:TipType.id; topic:string; isbn:string; url:string; description:string; comment:string; seen:boolean; hidden:boolean]
[TipAuthor|tip_id:Tip.id; author:string]
[TipTag|tip_id:Tip.id; tag:string]
[TipCourse|tip_id:Tip.id; course_id:Course.id]
[Course|id:integer; name:string]
[TipType]1-*[Tip]
[Tip]1-*[TipAuthor]
[Tip]1-*[TipTag]
[Tip]1-*[TipCourse]
[TipCourse]*-1[Course]
