## Database migrations

### Database migration after sprint2 release

```
ALTER TABLE book_tip DROP COLUMN type;  
ALTER TABLE book_tip ADD COLUMN studied BOOLEAN;  
UPDATE book_tip SET studied=false WHERE studied IS NULL;  
```

### Database migration after sprint3 release
```
ALTER TABLE book_tip DROP COLUMN prerequisite_courses;  
ALTER TABLE book_tip DROP COLUMN related_courses;  
ALTER TABLE book_tip ADD COLUMN description VARCHAR(255);  
  
ALTER TABLE blog_tip DROP COLUMN prerequisite_courses;  
ALTER TABLE blog_tip DROP COLUMN related_courses;  
ALTER TABLE blog_tip ADD COLUMN description VARCHAR(255);  
ALTER TABLE blog_tip ADD COLUMN type VARCHAR(255);  
UPDATE blog_tip SET type='blog' WHERE type IS NULL;  
  
ALTER TABLE blog_tip RENAME TO link_tip;  
ALTER INDEX blog_tip_pkey RENAME TO link_tip_pkey;  
```
