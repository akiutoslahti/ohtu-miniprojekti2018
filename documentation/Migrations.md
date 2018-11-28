## Database migrations

### Database migration after sprint2 release

```
ALTER TABLE book_tip DROP COLUMN type;  
ALTER TABLE book_tip ADD COLUMN studied BOOLEAN;  
UPDATE book_tip SET studied=false WHERE studied IS NULL;  
```

