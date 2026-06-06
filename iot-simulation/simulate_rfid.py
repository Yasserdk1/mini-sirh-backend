import requests
from datetime import datetime

API_URL = "http://localhost:8080/api/pointages"

def simulate_scan(rfid_code, device_id="PYTHON_SIM_01"):
    payload = {
        "rfidCode": rfid_code,
        "deviceId": device_id,
        "timestamp": datetime.now().isoformat()
    }

    try:
        response = requests.post(API_URL, json=payload)

        print("===================================")
        print("RFID Simulation")
        print("===================================")
        print(f"RFID Code : {rfid_code}")
        print(f"Device ID : {device_id}")
        print(f"Status    : {response.status_code}")
        print("Response  :")
        print(response.text)

    except requests.exceptions.ConnectionError:
        print("ERROR: Cannot connect to Spring Boot backend.")
        print("Make sure the backend is running on http://localhost:8080")


if __name__ == "__main__":
    print("Mini-SIRH RFID Simulation")
    print("-------------------------")
    rfid = input("Enter RFID code: ")

    simulate_scan(rfid)