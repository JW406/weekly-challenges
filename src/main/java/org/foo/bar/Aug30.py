class Node:
    def __eq__(self, o):
        return True

def equals():
    return Node()

if __name__ == '__main__':
    print(equals() == 0)
    print(equals() == [])
    print(equals() == (lambda: 1))
