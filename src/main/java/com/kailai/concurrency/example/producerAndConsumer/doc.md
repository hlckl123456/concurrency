
Benefit of Producer Consumer Pattern:

1.Can code producer and consumer independently and concurrently
    they just need to know the shared object.
    which lead to a more clean, readable and manageable code style.
2.Producer doesn't need to know who is consumer or how many consumers
    are there. Same is true with consumer
3.Producer and consumer can work with different speed. There is no risk of
    consumer consuming half-baked item. In fact by monitoring consumer speed,
    one can introduce more consumer for better utilization.